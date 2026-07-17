(ns culture.facts
  "Regional-culture catalog for Venice (Comune di Venezia) -- local dishes,
  protected products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"venezia"
   [{:culture/id "venezia.dish.sarde-in-saor"
     :culture/name "Sarde in saor"
     :culture/municipality "venezia"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Venetian antipasto of fried sardines seasoned with sweet-and-sour onions, pine nuts and raisins, originating as a preservation technique of Venetian fishermen."
     :culture/url "https://en.wikipedia.org/wiki/Sarde_in_saor"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "venezia.dish.baccala-mantecato"
     :culture/name "Baccalà mantecato"
     :culture/municipality "venezia"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Creamy emulsion of stockfish and olive oil typical of Venetian cuisine, traditionally served on the cicchetti of Venice's bacari wine bars."
     :culture/url "https://it.wikipedia.org/wiki/Baccal%C3%A0_mantecato"
     :culture/url-provenance :wikipedia-it
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "venezia.dish.risi-e-bisi"
     :culture/name "Risi e bisi"
     :culture/municipality "venezia"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Veneto spring dish of rice and green peas cooked with stock from the fresh pods and flavoured with pancetta, thick enough to resemble a risotto but served with a spoon."
     :culture/url "https://en.wikipedia.org/wiki/Risi_e_bisi"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "venezia.dish.fegato-alla-veneziana"
     :culture/name "Fegato alla veneziana"
     :culture/municipality "venezia"
     :culture/country "ITA"
     :culture/kind :dish
     :culture/summary "Venice-style variation of liver and onions whose recipe includes a dash of red wine or vinegar."
     :culture/url "https://en.wikipedia.org/wiki/Fegato_alla_veneziana"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "venezia.beverage.bellini"
     :culture/name "Bellini"
     :culture/municipality "venezia"
     :culture/country "ITA"
     :culture/kind :beverage
     :culture/summary "Cocktail of Prosecco and peach purée invented in Venice at Harry's Bar sometime between 1934 and 1948, named after a 15th-century Venetian painter."
     :culture/url "https://en.wikipedia.org/wiki/Bellini_(cocktail)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "venezia.craft.murano-glass"
     :culture/name "Murano glass"
     :culture/name-local "Vetro di Murano"
     :culture/municipality "venezia"
     :culture/country "ITA"
     :culture/kind :craft
     :culture/summary "Glassware made in Venice, typically on the island of Murano, where a law of 1291 confined most of Venice's glassmaking industry; Europe's major centre for luxury glass from the High Middle Ages to the Renaissance."
     :culture/url "https://en.wikipedia.org/wiki/Murano_glass"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "venezia.festival.carnevale-di-venezia"
     :culture/name "Carnival of Venice"
     :culture/name-local "Carnevale di Venezia"
     :culture/municipality "venezia"
     :culture/country "ITA"
     :culture/kind :festival
     :culture/summary "Annual festival held in Venice famous for its elaborate costumes and masks, ending on Shrove Tuesday; outlawed in 1797 and revived in 1979."
     :culture/url "https://en.wikipedia.org/wiki/Carnival_of_Venice"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "venezia.festival.festa-del-redentore"
     :culture/name "Festa del Redentore"
     :culture/municipality "venezia"
     :culture/country "ITA"
     :culture/kind :festival
     :culture/summary "Venetian festival held the third Sunday of July with fireworks over Saint Mark's basin, established in 1577 to commemorate the end of the 1576 plague."
     :culture/url "https://en.wikipedia.org/wiki/Festa_del_Redentore"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "venezia.heritage.basilica-di-san-marco"
     :culture/name "St Mark's Basilica"
     :culture/name-local "Basilica di San Marco"
     :culture/municipality "venezia"
     :culture/country "ITA"
     :culture/kind :heritage
     :culture/summary "Cathedral church of the Patriarchate of Venice on Saint Mark's Square, housing the relics of Saint Mark, the city's patron saint; the present church was begun probably in 1063 and consecrated in 1094."
     :culture/url "https://en.wikipedia.org/wiki/St_Mark%27s_Basilica"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-municipality-ita-venezia culture catalog "
                 "(ADR-2607171400): " (count (get catalog "venezia"))
                 " Venezia entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
