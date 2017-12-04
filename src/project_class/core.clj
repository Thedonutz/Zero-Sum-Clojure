(ns project-class.core)
  (use 'clojure.java.io)
  (use 'clojure.core)
  (use ['clojure.string :only '(split)])
(require '[com.climate.claypoole :as cp])
(require ['clojure.string :as 'str])

(defn str-2-int
  [string]
  (map #(Integer/parseInt %)
       (split string #" ")))
(defn read-lines []
  "Reads by line from File"
  (with-open [rdr (reader (str "resources/" (read-line)))]
     (doall (line-seq rdr))))

;;List manipulations
(defn convertedList []
  (apply str-2-int (read-lines)))
(defn workingList []
  (rest (convertedList)))

(defn trunc-to-even
  "Truncates a value the largest int less than or equal to value"
  [val]
  (* 2 (Math/floor (/ val 2))))

(defn split-2 [x]
  (let [half-even (trunc-to-even (/ (count x) 2))]
    (split-at half-even x )))
(defn split-4 [x]
  (apply concat (pmap split-2(split-2 x))))
(defn split-8 [x]
  (apply concat (pmap split-2(split-4 x))))
(defn split-16 [x]
  (apply concat (pmap split-2(split-8 x))))
(defn split-32 [x]
  (apply concat (pmap split-2(split-16 x))))

(defn choice [x]
  (cond
    (= (count x) 2) (max (first x), (last x))
    :else (max (+ (first x) (min (choice (rest (butlast x))),  (choice(rest(rest x))))),
               (+ (last x) (min (choice (butlast( butlast x))), (choice(rest (butlast x))))))))

(println "Welcome, To the Crazy Game: ")

(defn scoreSingle [x]
  (println "Player 1 will receive" (time(choice x))
           "Player 2 gets" (- (reduce + x) (choice x) )))
(defn scoreDouble [x]
  (println "Player 1 will receive" (reduce +(pmap choice(split-2 x))) 
           "Player 2 gets" (- (reduce + x) (reduce +(pmap choice(split-2 x))))))
(defn score4 [x]
  (println "Player 1 will receive" (reduce +(pmap choice(split-4 x))) 
           "Player 2 gets" (- (reduce + x) (reduce +(pmap choice(split-4 x))))))
(defn score8 [x]
  (println "Player 1 will receive" (reduce +(pmap choice(split-8 x))) 
           "Player 2 gets" (- (reduce + x) (reduce +(pmap choice(split-8 x))))))
(defn score16 [x]
  (println "Player 1 will receive" (reduce +(pmap choice(split-16 x))) 
           "Player 2 gets" (- (reduce + x) (reduce +(pmap choice(split-16 x))))))
(defn score32 [x]
  (println "Player 1 will receive" (reduce +(pmap choice(split-32 x))) 
           "Player 2 gets" (- (reduce + x) (reduce +(pmap choice(split-32 x))))))

;(println "Enter file for Single-Threaded Task")
;(scoreSingle (workingList))
;
;(println "Enter file for 2-Threaded Task")
;(scoreDouble (workingList))
;
;(println "Enter File for 4-Threaded Task")
;(score4 (workingList))
;
;(println "Enter File for 8-Threaded Task")
;(score8 (workingList))
;
;(println "Enter File for 16-Threaded Task")
;(score16 (workingList))
;
;(println "Enter File for 32-Threaded Task")
;(score32 (workingList))
