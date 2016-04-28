neuralNetworksData= read.csv("C:\\Users\\NeFi\\Downloads\\Problem8.csv")


install.packages("neuralnet")
require(neuralnet)

neuralNetworks <- neuralNetworksData[sample(1:nrow(neuralNetworksData), ),] 
# get a training sample from iris
nnet_iristrain <- neuralNetworks

# calculating By resilient Backpropogation Algorithm

nnet_iristrain <- cbind(nnet_iristrain, neuralNetworks$NumberOfTrips == '1')
nnet_iristrain <- cbind(nnet_iristrain, neuralNetworks$NumberOfTrips == '2')
nnet_iristrain <- cbind(nnet_iristrain, neuralNetworks$NumberOfTrips == '3')
nnet_iristrain <- cbind(nnet_iristrain, neuralNetworks$NumberOfTrips == '4')
nnet_iristrain <- cbind(nnet_iristrain, neuralNetworks$NumberOfTrips == '5')
nnet_iristrain <- cbind(nnet_iristrain, neuralNetworks$NumberOfTrips == '6')
nnet_iristrain <- cbind(nnet_iristrain, neuralNetworks$NumberOfTrips == '7')
nnet_iristrain <- cbind(nnet_iristrain, neuralNetworks$NumberOfTrips == '8')
nnet_iristrain <- cbind(nnet_iristrain, neuralNetworks$NumberOfTrips == '9')
nnet_iristrain <- cbind(nnet_iristrain, neuralNetworks$NumberOfTrips == '10')

names(nnet_iristrain)[8] <- 'r1'
names(nnet_iristrain)[9] <- 'r2'
names(nnet_iristrain)[10] <- 'r3'
names(nnet_iristrain)[11] <- 'r4'
names(nnet_iristrain)[12] <- 'r5'
names(nnet_iristrain)[13] <- 'r6'
names(nnet_iristrain)[14] <- 'r7'
names(nnet_iristrain)[15] <- 'r8'
names(nnet_iristrain)[16] <- 'r9'
names(nnet_iristrain)[17] <- 'r10'



nn = neuralnet(r1+r2+r3+r4+r5+r6+r7+r8+r9+r10~PRCP+SNWD+SNOW+Temp+Holiday+Weekday
               ,data = nnet_iristrain, hidden=2, learningrate = 0.01,linear.output = FALSE)
nn
plot(nn)

neuralNetworksData[-1]
mypredict <- compute(nn, neuralNetworksData[-1])$net.result

maxidx <- function(arr) {
  return(which(arr == max(arr)))
}

idx <- apply(mypredict, c(1), maxidx)
prediction <- c('r1', 'r2', 'r3','r4','r5','r6','r7','r8','r9','r10')[idx]
table(prediction, neuralNetworksData$NumberOfTrips)



#prediction  1  2  3  4  5  6  7  8  9 10
#r10 54 72 80 70 62 61 71 61 80 84
#r2   1  1  0  0  0  0  0  2  0  0
#r4   0  0  1  2  0  0  2  0  1  1
#r6   0  0  2  0  2  0  1  0  1  0
#r9   0  0  1  1  2  2  2  3  5  2

new.output = compute(nn,covariate = matrix(c(0.13,0,0,62.5,0,1), byrow=TRUE,ncol=6))
new.output$net.result
new.output


nn.new1=ifelse(new.output$net.result[[1]]>0.5,1,0)

