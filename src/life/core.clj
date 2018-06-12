(ns life.core
  (:gen-class))

(require '[clojure.string :as string])
(require '[lanterna.screen :as s])

(defn sum [coll] (reduce + 0 coll))
(defn to-cell [v, x, y] { :v v :x x :y y })
(defn unwrap [cell] (get cell :v))
(defn margin [num, x] (int (/ (- num x) 2)))

(defn get-neighbors [board, cell]
  (let [x (get cell :x) 
        y (get cell :y)]
    (sum 
      (map unwrap 
        (list
          (nth (nth board (- y 1) 0) x 0)
          (nth (nth board (- y 1) 0) (- x 1) 0)
          (nth (nth board (- y 1) 0) (+ x 1) 0)
          (nth (nth board y 0) (- x 1) 0)
          (nth (nth board y 0) (+ x 1) 0)
          (nth (nth board (+ y 1) 0) x 0)
          (nth (nth board (+ y 1) 0) (- x 1) 0)
          (nth (nth board (+ y 1) 0) (+ x 1) 0))))))

(defn lives? [cell, neighbors]
  (if (= cell 0)
    (if (>= neighbors 3) 1 0)
    (if (or (= neighbors 2) (= neighbors 3)) 1 0)))
      
(defn board [x, y]
  (to-array-2d 
    (repeat y 
      (repeat x 
        (to-cell (rand-int 2) x y)))))

(defn -main [& args]
  (let [scr (s/get-screen)]
    (s/start scr)
    (s/redraw scr)))

