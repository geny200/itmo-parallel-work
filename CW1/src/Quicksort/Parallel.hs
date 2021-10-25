module Quicksort.Parallel
  ( parQuickSort,
  )
where

import Control.Parallel (par, pseq)

parQuickSort :: Ord a => [a] -> [a]
parQuickSort [] = []
parQuickSort (x : xs) =
  let lesser = parQuickSort (filter (< x) xs)
      greater = parQuickSort (filter (>= x) xs)
   in ($! lesser)
        `par` ($! greater)
        `pseq` (lesser ++ [x] ++ greater)
