(ns project-class.core)
  (use 'clojure.java.io)
  (use ['clojure.string :only '(split)])

(require ['clojure.string :as 'str])
(println "Welcome, To the Crazy Game: ")

(defn str-2-int
  [string]
  (map #(Integer/parseInt %)
       (split string #" ")))

(defn read-lines []
  "Reads by line from File"
  (with-open [rdr (reader (str "resources/" (read-line)))]
     (doall (line-seq rdr))))

(defn convertedList []
  (apply str-2-int (read-lines)))

(defn choice [x]
  (+ (first x) (min (first( rest x)), (first(rest(reverse x))), 
                    (first(rest(rest x))), (first(reverse x)))))
(defn altChoice [x]
  (+ (last x) (min (first x), (last (butlast (butlast x))), 
                                    (first (rest x)), (last (butlast x)))))
(defn mainAlg [x]
 (cond
   (> (choice x) (altChoice x)) (first x)
   :else (last x)))

(defn listReturned [x]
  (cond
    (> (choice x) (altChoice x)) (rest x)
    :else (drop-last x)))

(defn mainProg [x]
  (println "From this GameFile ")
(loop [a (rest x), player1 0, player2 0, s (first x)]
  ;(println "list has" a "with size" s)
  ;(println "current Score:" player1 player2) ;--for Debugging
  (cond
    (<= s 1) (println "Final Score: Player1 will receive " player1 "Points", "Player2 will receive" player2 "Points")
    (= s 2) (recur a (+ (max (first a), (last a)) player1) (+ (min (first a), (last a)) player2) 0)
    (= (mod s 2) 0)    (recur (listReturned a) (+ player1 (mainAlg a)) player2 (dec s))
    :else    (recur (listReturned a) player1 (+ player2 (mainAlg a)) (dec s)))))

(println " Enter file you wish to read from in 'file.txt' format from Resources. ")  
(time (mainProg (convertedList)))

  