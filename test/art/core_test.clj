(ns art.core-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [art.core :refer :all]))

(def defaults 
  {:n 0 :step 0 :width 10 :height 5 :direction :down})

(facts
  (step defaults)
  => {:n 0 :step 1 :width 10 :height 5 :direction :down :lines []}

  (-> defaults
      (assoc :n 2 :width 9)
      (step)
      :lines)
  => [0 4 8]

  (-> defaults
      (assoc :direction :down :height 3 :step 3)
      (step)
      :direction)
  => :up

  (-> defaults
      (assoc :direction :down :height 3 :step 3)
      (step)
      :step)
  => 3

  (-> defaults
      (assoc :direction :up :step 0)
      (step)
      :direction)
  => :down

  (-> defaults
      (assoc :direction :up :step 0)
      (step)
      :step)
  => 0
  )
