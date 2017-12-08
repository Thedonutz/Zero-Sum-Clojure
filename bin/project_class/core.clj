(ns project-class.core)
  (use 'clojure.java.io)
  (use 'clojure.core)
  (use ['clojure.string :only '(split)])
(require '[com.climate.claypoole :as cp])
(require ['clojure.string :as 'str])
(import java.util.concurrent.Executors)
(import '[java.util.concurrent Executors ExecutorService Callable])

(defn str-2-int
  ;;parse read-string to List;;
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
  (apply concat (pmap split-4(split-2 x))))
(defn split-16 [x]
  (apply concat (pmap split-8(split-2 x))))
(defn split-32 [x]
  (apply concat (pmap split-16(split-2 x))))

(defn choice [x]
  (cond
    (= (count x) 2) (max (first x), (last x))
    :else (max (+ (first x) (min ( choice (rest (butlast x))), ( choice(rest(rest x))))),
               (+ (last x) (min ( choice (butlast( butlast x))), ( choice(rest (butlast x))))))))

(println "Welcome, To the Crazy Game: ")
(println "Enter file size you wish to play. (add .txt on the end)")
(defn scoreSingle [x]
  (println "Player 1 will receive" (time(choice x))
           "Player 2 gets" (- (reduce + x) (choice x) )))
(defn scoreDouble [x]
  (println "Player 1 will receive" (time (reduce +(pmap choice(split-2 x))))
           "Player 2 gets" (- (reduce + x) (reduce +(pmap choice(split-2 x))))))
(defn score4 [x]
  (println "Player 1 will receive" (time (reduce +(pmap choice(split-4 x))) )
           "Player 2 gets" (- (reduce + x) (reduce +(pmap choice(split-4 x))))))
(defn score8 [x]
  (println "Player 1 will receive" (time (reduce +(pmap choice(split-8 x))) )
           "Player 2 gets" (- (reduce + x) (reduce +(pmap choice(split-8 x))))))
(defn score16 [x]
  (println "Player 1 will receive" (time (reduce +(pmap choice(split-16 x))) )
           "Player 2 gets" (- (reduce + x) (reduce +(pmap choice(split-16 x))))))
(defn score32 [x]
  (println "Player 1 will receive" (time (reduce +(pmap choice(split-32 x))) )
           "Player 2 gets" (- (reduce + x) (reduce +(pmap choice(split-32 x))))))

(scoreSingle (workingList))
(println "Enter file size you wish to play. (add .txt on the end)")
(scoreDouble (workingList))
(println "Enter file size you wish to play. (add .txt on the end)")
(score4 (workingList))
(println "Enter file size you wish to play. (add .txt on the end)")
(score16 (workingList))
(println "Enter file size you wish to play. (add .txt on the end)")
(score32 (workingList))

;(defn test-prog [nthreads]
;  (let [pool (Executors/newFixedThreadPool nthreads)
;        tasks (choice(workingList))]
;      (let [ret (.invokeAll pool tasks)]
;        (.shutdown pool)
;        (map #(.get %) ret))))
