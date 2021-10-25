module Quicksort.Line
  ( lineQuickSort,
  )
where

import Data.Vector.Generic (Vector, modify)
import Data.Vector.Generic.Mutable as M (drop, length, read, splitAt, unstablePartition)

lineQuickSort :: (Vector v a, Ord a) => v a -> v a
lineQuickSort = modify quickSortStep
  where
    quickSortStep xs
      | M.length xs < 2 = return ()
      | otherwise =
        do
          pivot <- M.read xs (M.length xs `div` 2)
          splitIndex <- M.unstablePartition (< pivot) xs
          let (lesser, greaterOrEq) = M.splitAt splitIndex xs
          equaledIndex <- M.unstablePartition (== pivot) greaterOrEq
          quickSortStep lesser
          quickSortStep $ M.drop equaledIndex greaterOrEq
