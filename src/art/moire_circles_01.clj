(ns art.moire-circles-01
  (:require [quil.core :as q]
            [quil.middleware]))

; en.wikipedia.org/wiki/Moir%C3%A9_pattern#/media/File:Moire_Circles.svg

; (def width  405)
; (def height 245)
; (def width  810)
; (def height 540)
(def width 1080)
(def height 720)

(def step-size 40)


(defn circles [max-size]
  (let [step-size (* (q/width) 0.1)
        sizes (->> (range)
                   (map #(* step-size %))
                   (drop 1)
                   (take-while #(< % max-size)))]
    (doseq [size sizes]
      (q/ellipse 0 0 size size))))

(defn frame->offset [frame]
  (-> frame
      (mod 180)
      (q/radians)
      (Math/tan)
      (* (q/width) 0.2)))


(defn render [n]
  (q/stroke 240)
  (q/stroke-weight (* (q/width) 0.017))

  (q/translate (/ (q/width) 2) (/ (q/height) 2))
  (q/background 0)

  (let [left (frame->offset n)
        right (- 0 left left)
        max-size (* 2.2 (q/width))]

    (q/translate left 0)
    (circles max-size)

    (q/translate right 0)
    (circles max-size)

    ; (if (> 180 (q/frame-count))
    ;   (q/save-frame "moire-####.png")
    ;   (q/exit))
    ))

(defn setup []
  (q/fill 0 0)
  90
  )

(defn step [n]
  (-> n
      (+ 1)
      (mod 180)))

(defn draw [state]
  (render state))

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
