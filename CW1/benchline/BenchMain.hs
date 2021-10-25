{-# LANGUAGE BangPatterns #-}

import Criterion.Main (bench, bgroup, defaultMain, whnf)
import Data.List (sort)
import qualified Data.Vector.Unboxed as U
import Quicksort.Line (lineQuickSort)
import System.Random (mkStdGen, randomRs)

-- | Function for running benchmarks
main :: IO ()
main =
  do
    let !randomSeq = take 10000000 (randomRs ((-1000000, 1000000) :: (Int, Int)) (mkStdGen 1242))
        !randomVector = U.fromList randomSeq
    defaultMain
      [ bgroup
          "Linear quick sort "
          [bench "10^7" $ whnf lineQuickSort randomVector],
        bgroup
          "std sort"
          [bench "10^7" $ whnf sort randomSeq]
      ]
