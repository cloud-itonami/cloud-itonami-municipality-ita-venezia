(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Venice (Comune di Venezia) --
  a Wave 1b addition per ADR-2607171400 addendum 2, joining the
  cloud-itonami-municipality-* compliance-fact family of ADR-2607141700
  (cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL comune.venezia.it PDF -- never fabricated.
  An ordinance not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/number.

  Both entries below were verified on 2026-07-17 by downloading each
  source PDF from comune.venezia.it (regulations index:
  comune.venezia.it/it/content/regolamenti) and directly reading the PDF
  text via the Read tool: each title, deliberation number and date stated
  here appears in the front matter of the cited PDF. The Regolamento
  Edilizio preface incidentally names officials -- those names were read
  only to locate the approval data and are NOT stored anywhere in this
  catalog, consistent with this family's no-personal-names discipline.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"venezia"
   [{:ordinance/id "venezia.regolamento-edilizio-2019"
     :ordinance/title "Regolamento Edilizio"
     :ordinance/municipality "venezia"
     :ordinance/country "ITA"
     :ordinance/kind :ordinance
     :ordinance/number "Approvato con DCC n. 70 del 13/12/2019; pubblicato dal 04/02/2020; esecutivo dal 15/02/2020"
     :ordinance/url "https://www.comune.venezia.it/sites/comune.venezia.it/files/page/files/reg_edilizio_dal_15-02-2020.pdf"
     :ordinance/url-provenance :official-comune-venezia-it
     :ordinance/enacted-date "2019-12-13"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:building :urban-planning}}
    {:ordinance/id "venezia.regolamento-polizia-urbana-1987"
     :ordinance/title "Regolamento di polizia urbana"
     :ordinance/municipality "venezia"
     :ordinance/country "ITA"
     :ordinance/kind :ordinance
     :ordinance/number "Deliberazione del Consiglio Comunale n. 454 del 02/03/1987, da ultimo modificato con Deliberazione del Consiglio Comunale n. 63 del 29/11/2016"
     :ordinance/url "https://www.comune.venezia.it/sites/comune.venezia.it/files/page/files/Regolamento%20di%20polizia%20urbana%20CON%20MODIFICHE%20-DCC%2063%20del%202017.pdf"
     :ordinance/url-provenance :official-comune-venezia-it
     :ordinance/enacted-date "1987-03-02"
     :ordinance/last-revised-date "2016-11-29"
     :ordinance/retrieved-at "2026-07-17"
     :ordinance/topic #{:public-order :public-space}}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-ita-venezia Wave 1b (ADR-2607171400 "
                 "addendum 2 / family ADR-2607141700): "
                 (count (get catalog "venezia")) " Venezia entries seeded with "
                 "official comune.venezia.it citations. Extend "
                 "`ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
