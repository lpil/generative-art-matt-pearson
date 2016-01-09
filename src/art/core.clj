(ns art.core
  (:require [quil.core :as q]
            [quil.middleware]))

(def width  810)
(def height 540)
; (def width 1080)
; (def height 720)

(defn- line [x]
    (q/line x 0 x height))

(defn- next-step [{step :step height :height direction :direction}]
  (if (= direction :down)
    ; Going down...
    (if (>= step height)
      {:direction :up   :step step}
      {:direction :down :step (+ step 1)})
    ; Going up...
    (if (<= step 0)
      {:direction :down :step step}
      {:direction :up   :step (- step 1)})))


(defn- calc-lines [state]
  (let [nums   (range (+ 1 (:n state)))
        width  (- (:width state) 1)
        n      (:n state)
        calc-x #(/ (* width %) n)]
    (if (= 0 n)
      []
      (map calc-x nums))))


(defn setup []
  (q/stroke 255)
  (q/stroke-weight 1)
  {:n 15 :step 0 :height height :width width :direction :down})


(defn step [state]
  (let [lines (calc-lines state)
        {step :step direction :direction} (next-step state)]
    (assoc state
           :lines lines
           :step step
           :direction direction)))


(defn draw [state]
  (q/background 0)
  (doseq [x (:lines state)] (line x))
  (q/stroke))


(defn run []
  (q/defsketch example
    :title "Art!"
    :middleware [quil.middleware/fun-mode]
    :settings #(q/smooth 2)
    :setup setup
    :update step
    :draw draw
    :size [width height]
    ))
; (run)
