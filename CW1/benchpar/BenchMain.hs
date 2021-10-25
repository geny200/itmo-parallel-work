{-# LANGUAGE BangPatterns #-}

import Criterion.Main (bench, bgroup, defaultMain, whnf)
import Quicksort.Parallel (parQuickSort)
import System.Random (mkStdGen, randomRs)

-- | Function for running benchmarks
main :: IO ()
main =
  do
    let !randomSeq = take 10000000 (randomRs ((-1000000, 1000000) :: (Int, Int)) (mkStdGen 1242))
    defaultMain
      [ bgroup
          "Parallel quick sort "
          [bench "10^7" $ whnf parQuickSort randomSeq]
      ]
