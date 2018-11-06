data Season =  Fall | Winter | Summer | Spring
    deriving(Eq, Show)
data Month =  January | February | March | April | May | June | July | August | September | October | November | December
    deriving(Eq, Show)

--1
months:: Season -> (Month, Month, Month)
months Fall = (October, November, December)
months Winter = (January, February, March)
months Summer = (July, August, September)
months Spring = (April, May, June)

--2 
