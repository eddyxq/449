import Data.Char
import Data.List
--1
factorial::Integer->Integer
factorial 0 = 1
factorial n = n * factorial(n-1)

productFactorial::Integer->Integer
productFactorial 0 = 1
productFactorial n = productFactorial(n-1) * factorial(n) 
--2
smallestFactor::Integer->Integer
smallestFactor n = (allDivisors n) !! 1
--3
gameOddEven::Integer->[Integer]
gameOddEven n 
    | n == 1 = [1]
    | mod n 2 == 0 = n:gameOddEven(div n 2)
    | otherwise = n:gameOddEven(n*3+1)
--4
isGoodPassword::[Char]->Bool
isGoodPassword s 
    | length s > 7 && any isUpper s && any isLower s && any isDigit s && any isLetter s = True
    | otherwise = False
--5
isPrime::Integer->Bool
isPrime n
    | n == 1 || smallestFactor(n) /= n = False
    | otherwise = True
--6
allDivisors::Integer->[Integer]
allDivisors n = [x | x <- [1..n], mod n x == 0]
--7
matches::Integer->[Integer]->[Integer]
matches n l = [x | x <- l, x == n]
--8
solveQuadraticEquation::Double->Double->Double->(Double,Double)
solveQuadraticEquation a b c = (x1,x2)
    where
    x1 = e+sqrt d/(2*a)
    x2 = e-sqrt d/(2*a)
    d = b*b-4*a*c
    e = -b/(2*a)
--9
occursIn::Integer->[Integer]->Bool
occursIn x xs = x `elem` xs   
--10
allOccurIn::[Integer]->[Integer]->Bool
allOccurIn xs ys = length xs == length [z|z<-xs, occursIn z ys]
--11
removeDuplicate::Eq a=>[a]->[a]
removeDuplicate [] = []
removeDuplicate (x:xs)   
    | x `elem` xs = removeDuplicate xs
    | otherwise = x:removeDuplicate xs

sameElements::[Integer]->[Integer]->Bool
sameElements xs ys = removeDuplicate (sort ys) == sort xs
--12
numOccurrences::Integer->[Integer]->Int
numOccurrences n xs = length [x|x<-xs, n==x]
--13
allUrls::String->[String]
allUrls s = [x|x<-(words s), isPrefixOf "http://" x ]
--14
sieve::[Integer] -> [Integer]
sieve xs = [x|x<-xs, isPrime x]
--15 
pascal::Int->[Int]
pascal 1 = [1]
pascal n = [1]++zipWith(+)(pascal(n-1))(tail(pascal(n-1)))++[1]
