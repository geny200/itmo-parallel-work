{-# LANGUAGE ScopedTypeVariables #-}

module Main where

import Data.Vector.Unboxed (fromList)
import Quicksort.Line (lineQuickSort)
import Quicksort.Parallel (parQuickSort)

main :: IO ()
main =
  do
    print (lineQuickSort myVector)
    print (parQuickSort myList)
  where
    myList :: [Int] = [0, 0, 1, 0, -1]
    myVector = fromList myList
