(ns ordinance.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest venezia-has-spec-basis
  (let [sb (facts/spec-basis "venezia")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://www.comune.venezia.it/") sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "roma")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["venezia" "roma"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["roma"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["venezia.regolamento-polizia-urbana-1987"]
         (mapv :ordinance/id (facts/by-topic "venezia" :public-order))))
  (is (empty? (facts/by-topic "venezia" :labor)))
  (is (empty? (facts/by-topic "roma" :building))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/datascript-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
