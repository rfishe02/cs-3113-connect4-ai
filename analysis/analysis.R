
# AI HOMEWORK

par(lwd = 2)

random <- read.csv(file.choose(),header=TRUE)
random2 <- read.csv(file.choose(),header=TRUE)

par(mfrow=c(1,2),lwd = 2,cex=1.25)

tot <- aggregate(round ~ win,data = random,FUN = length)
pie(c(tot[1,2],tot[2,2]), labels=c("First Utility","Random"), col = c("white","gray"))

tot2 <- aggregate(round ~ win,data = random2,FUN = length)
pie(c(tot2[1,2],tot2[2,2]), labels=c("Second Utility","Random"), col = c("white","gray"))

outcome3 <-read.csv(file.choose(),header=TRUE)

par(lwd = 2,cex=1.25)
tot3 <- aggregate(round ~ win,data = outcome3,FUN = length)
pie(c(tot3[1,2],tot3[2,2],tot3[3,2]), labels=c("Tie","First Utility","Second Utility"),main="First Utility vs. Second Utility in 300 Matches",col = c("black","white","gray"))

tot[1,2]/sum(tot[1,2],tot[2,2])
tot2[1,2]/sum(tot2[1,2],tot2[2,2])
tot3[3,2]/sum(tot3[1,2],tot3[2,2],tot3[3,2])

####

outcome <- read.csv(file.choose(),header=TRUE)
outcome2 <- read.csv(file.choose(),header=TRUE)

par(mfrow=c(1,2),lwd = 2,cex=1.25)

tot <- aggregate(round ~ win,data = outcome,FUN = length)
pie(c(tot[1,2],tot[2,2],tot[3,2]), labels=c("Tie","First Utility 1","First Utility 2"),col = c("black","white","gray"))

tot2 <- aggregate(round ~ win,data = outcome2,FUN = length)
pie(c(tot2[1,2],tot2[2,2]), labels=c("Second Utility 1","Second Utility 2"),col = c("white","gray"))

tot[2,2]/sum(tot[1,2],tot[2,2],tot[3,2])
tot2[1,2]/sum(tot2[1,2],tot2[2,2])

