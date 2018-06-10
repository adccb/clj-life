(ns life.core-test
  (:require [clojure.test :refer :all]
            [life.core :refer :all]))

;; helper fns
(defn get-rand-board-elem [b]
  (get (get b (rand-int 10)) (rand-int 10)))

;; test bodies
(deftest cell-lives
  (testing "correctly handles dead cells"
    (is (= 0 (lives? 0 (list 0 0 0 0 0))))
    (is (= 1 (lives? 0 (list 0 0 1 1 1)))))
    
  (testing "correctly handles live cells"
    (is (= 0 (lives? 1 (list 0 0 0 0 0))))
    (is (= 1 (lives? 1 (list 0 0 0 1 1))))
    (is (= 1 (lives? 1 (list 0 0 1 1 1)))))) 

(deftest game-board
  (testing "correctly generates a 10x10 board"
    (is (= 10 (alength board)))
    (is (= 10 (alength (get board 0))))
    (is (integer? (get-rand-board-elem board)))
    (is (<= 0 (get-rand-board-elem board) 1))))

