(ns life.core-test
  (:require [clojure.test :refer :all]
            [life.core :refer :all]))

;; helper fns
(defn no-op [args])
(defn get-rand-board-elem [b]
  (get (get b (rand-int 10)) (rand-int 10)))

;; test bodies
(deftest cell-conversion
  (testing "correctly wraps ints in cells"
    (is (= {:v 0 :x 0 :y 0} (to-cell 0 0 0))))
    
  (testing "correctly unwraps cells back to ints"
    (let [cell (to-cell 0 0 0)]
      (is (= 0 (unwrap cell))))))

(deftest cell-lives
  (testing "correctly handles dead cells"
    (is (= 0 (lives? 0 2)))
    (is (= 1 (lives? 0 3))))
    
  (testing "correctly handles live cells"
    (is (= 0 (lives? 1 1)))
    (is (= 1 (lives? 1 2)))
    (is (= 1 (lives? 1 3))))) 

(deftest game-board
  (testing "correctly generates a 10x10 board"
    (let [b (board 10 10)]
      (is (= 10 (alength b)))
      (is (= 10 (alength (get b 0)))))

    (let [b (board 15 10)]
      (is (= 10 (alength b)))
      (is (= 15 (alength (get b 0))))))

  (testing "generates valid cells"
    (let [b (board 10 10)]
      (is (integer? (unwrap (get-rand-board-elem b))))
      (is (<= 0 (unwrap (get-rand-board-elem b)) 1)))))

(deftest get-margin
  (testing "correctly determines the necessary margin"
    (is (= 5 (margin 20 10)))
    (is (= 10 (margin 24 4)))
    (is (= 7 (margin 25 10)))))

(deftest summing-numbers
  (testing "correctly sums a list of numbers"
    (is (= 15 (sum (list 1 2 3 4 5))))
    (is (= 30 (sum (list 10 10 10))))))

(deftest neighbor-calculations
  (testing "correctly determines the number of live neighbors"
    (let [b (list
              (list (to-cell 0 0 0) (to-cell 0 0 1) (to-cell 1 0 2))
              (list (to-cell 0 1 0) (to-cell 0 1 1) (to-cell 1 1 2))
              (list (to-cell 1 2 0) (to-cell 0 2 1) (to-cell 0 2 2)))
          c (nth (nth b 1 0) 1 0)]
     (is (= (get-neighbors b c) 3)))))


;(deftest tick-fn
;  (testing "correctly computes a tick"
;    (let [b (list
;            (list 0 0 0)
;            (list 0 1 0)
;            (list 0 1 1))
;          ticked (list
;            (list 0 0 0)
;            (list 0 1 1)
;            (list 0 1 0))]
;    (is (= (tick b) ticked)))))

