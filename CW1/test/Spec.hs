import Data.List (sort)
import Data.Vector.Unboxed (fromList, toList)
import Quicksort.Line (lineQuickSort)
import Quicksort.Parallel (parQuickSort)
import Test.Hspec (SpecWith, describe, hspec, it, shouldBe)
import Test.QuickCheck.Property (property)

-- | Function for running benchmarks
main :: IO ()
main =
  do
    hspec $ do
      testQuickSort

-- | Unit tests and Property-based tests for the @stringSum@
testQuickSort :: SpecWith ()
testQuickSort = describe "Task - Quicksort" $ do
  unitIntTest (toList . lineQuickSort . fromList)
  unitIntTest parQuickSort
  pTestStream (toList . lineQuickSort . fromList)
  pTestStream parQuickSort

-- | Data for unit testing
testData :: [[Int]]
testData =
  [ [1 .. 20],
    [1, 3 .. 20],
    [1, 6 .. 50],
    [20, 19 .. -20],
    [100, 90 .. 0],
    [20, 15 .. -100]
  ]

-- | Tester
uTest :: (Ord a, Show a) => ([a] -> [a]) -> [a] -> IO ()
uTest fun list =
  fun list `shouldBe` sort list

-- | Unit tests
unitIntTest :: ([Int] -> [Int]) -> SpecWith ()
unitIntTest fun = it "unit tests for quickSort" $ do
  foldr ((>>) . uTest fun) (pure ()) testData

lambda :: ([Int] -> [Int]) -> [Int] -> Bool
lambda fun xs = fun xs == sort xs

-- | Property-based tests
pTestStream :: ([Int] -> [Int]) -> SpecWith ()
pTestStream fun = it "property tests for quickSort" $ do
  property (lambda fun)
