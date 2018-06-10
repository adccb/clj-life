(ns life.core
  (:gen-class))

(defn lives? [cell, neighbors]
  (let [num (reduce #(+ %1 %2) 0 neighbors)]
    (if (= cell 0)
      (if (>= num 3) 1 0)
      (if (or (= num 2) (= num 3)) 1 0))))

(def board
  (to-array-2d (repeat 10 (repeat 10 (rand-int 2)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

