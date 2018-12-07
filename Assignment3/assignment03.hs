--1
data Season =  Fall | Winter | Summer | Spring
    deriving(Eq, Show)
data Month =  January | February | March | April | May | June | July | August | September | October | November | December
    deriving(Eq, Show)

months :: Season -> (Month, Month, Month)
months Fall = (October, November, December)
months Winter = (January, February, March)
months Summer = (July, August, September)
months Spring = (April, May, June)

--2 
data Form = And Form Form | Or Form Form | Not Form | Val Bool
    deriving(Eq, Show)

eval :: Form -> Bool
eval (Val a) = a
eval (Not a) = not (eval a)
eval (And a b) = eval a && eval b
eval (Or a b) = eval a || eval b

--3
data NTree = Leaf Int | Node NTree Int NTree
    deriving(Show)

collapse :: NTree -> [Int]
collapse (Leaf n) = [n]
collapse (Node a b c) = collapse a ++ [b] ++ collapse c

--4
data PTree a = PLeaf | PNode a (PTree a) (PTree a)
    deriving (Eq, Show, Ord)

countLeaves :: PTree a -> Integer
countLeaves PLeaf = 1
countLeaves (PNode a left right) = ((countLeaves left)) + ((countLeaves right))

--5 
data Store = Empty | Join Int Store Store
    deriving (Eq, Show, Ord)

maxStore :: Store -> Int
maxStore Empty = 0
maxStore (Join n a b) 
    | a == Empty && b == Empty = n
    | otherwise = max n (max (maxStore a) (maxStore b))

--6
data Expr = Num Integer | BinOp Op Expr Expr
    deriving (Eq,Show)
data Op = Add | Mul
    deriving (Eq,Show)

example = BinOp Add (Num 1)(BinOp Add (Num 2)(BinOp Mul (Num 3) (Num 4)))

countOp :: Op -> Expr -> Int
countOp op (Num n) = 0
countOp op (BinOp a b c) 
    | op == a = 1 + countOp a b + countOp a c
    | otherwise = countOp a b + countOp a c

--7 
data Tree a = Nil | Value a (Tree a) (Tree a)
    deriving (Show)

countChars :: String -> Integer
countChars s = toInteger (length s)

a = (Value "foo" (Value "bar" (Value "baz" Nil Nil)Nil) (Value "bam" Nil Nil))

result = mapTree countChars (Value "78" (Value "br" (Value "bz" Nil Nil) Nil) (Value "am" Nil Nil))

mapTree :: (a -> b) -> Tree a -> Tree b
mapTree f Nil = Nil
mapTree f (Value n left right) = Value (f n) (mapTree f left) (mapTree f right) 

--8

tree = (Value "78" (Value "br" (Value "bz" Nil Nil)Nil) (Value "am" Nil Nil))

foldTree :: (a -> a -> a) -> a -> Tree a -> a
foldTree f n Nil = n
foldTree f n (Value a left right) = f ( f n a) (f (foldTree f n left) (foldTree f n right))

--9
data Road = City String | Fork Road Road
    deriving (Show)

middleOfNowhere = Fork ( City "Banff" ) ( Fork ( Fork ( City "Edmonton" ) ( City "RedDeer" ) ) ( City "Calgary" ))

reachable :: String -> Road -> Bool
reachable loc (City c) = c == loc
reachable loc (Fork l r) = reachable loc l || reachable loc r

--10
data LR = L | R
    deriving (Show)

test_insertRoad = insertRoad (City "Kananaskas",L) "Calgary" middleOfNowhere

insertRoad :: (Road,LR) -> String -> Road -> Road
insertRoad (City new, L) c (City old) = (Fork (City new) (City c))
insertRoad (City new, R) c (City old) = (Fork (City c) (City new)) 
insertRoad (City new, s) c (Fork left right)
    | reachable c left = Fork (insertRoad(City new, s) c left) right
    | reachable c right = Fork left (insertRoad(City new, s) c right)

--Additional Challenge

data Instruction = FORW Int | BACKW Int | LEFT | RIGHT
    deriving (Eq, Show)

move_up :: [Instruction] -> (Int,Int) -> (Int,Int)
move_up [] (x,y) = (x,y)
move_up (z:zs) (x,y) 
    | z == LEFT = move_left (zs) (x,y)
    | z == RIGHT = move_right (zs) (x,y)
    | otherwise = move_up (zs)(x,y+(move z)) 

move_down :: [Instruction] -> (Int,Int) -> (Int,Int)
move_down [] (x,y) = (x,y)
move_down (z:zs) (x,y) 
    | z == LEFT = move_right (zs) (x,y)
    | z == RIGHT = move_left (zs) (x,y)
    | otherwise = move_down (zs) (x,y-(move z)) 

move_right :: [Instruction] -> (Int,Int) -> (Int,Int)
move_right [] (x,y)  = (x,y)
move_right (z:zs) (x,y) 
    | z == LEFT = move_up (zs) (x,y) 
    | z == RIGHT = move_down (zs) (x,y) 
    | otherwise = move_right (zs) (x+move z, y) 

move_left :: [Instruction] -> (Int,Int) -> (Int,Int)
move_left [] (x,y)  = (x,y)
move_left (z:zs) (x,y) 
    | z == LEFT = move_down (zs) (x,y) 
    | z == RIGHT = move_up (zs) (x,y) 
    | otherwise = move_left (zs) (x-move z, y) 

move :: Instruction -> Int
move (FORW x) = x
move (BACKW x) = -x

destination :: [Instruction] -> (Int, Int)
destination (z:zs)
    | z == LEFT = move_left(zs) (0,0) 
    | z == RIGHT = move_right (zs)(0,0) 
    | otherwise = move_up(z:zs) (0,0) 
