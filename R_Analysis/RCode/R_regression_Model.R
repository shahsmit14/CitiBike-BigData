install.packages("psych")
install.packages("MASS")
install.packages("car")

## input file for regression and times series analysis

trip.data <- read.csv("D:/BigData/ProjectDataSet/R_Analysis_data/Weather_Holiday_Data.csv", stringsAsFactors=FALSE)

#within(trip.data, Holiday <- factor(Holiday, labels = c(1, 0))

#### ANALYSIS

### Multi-variate regression analysis
reg.model <- lm(NumberOfTrips ~ PRCP + SNWD + SNOW + Temp + as.factor(Holiday) + as.factor(Weekday), data=trip.data)
summary(reg.model)

Call:
  lm(formula = NumberOfTrips ~ PRCP + SNWD + SNOW + Temp + Holiday + 
       Weekday, data = trip.data)
?lm

#comments on above stats
#The p-value corresponsding to F-statistic is greater than 0.001 with a 0.001 level of significance. 
#It indicates that the overall model is not significant.
#The R-square value suggest that only 0.8% variance is explained by the model, which is pretty bad.
#Its is possible that the model has violated the assumptions of linearity.



#1. Are any model assumptions violated?
par(mfrow=c(2,2))
plot(reg.model)

#The red line is not smooth and drifs away from zero as well. This might still satisfy the linearity assumption.
#The residuals vs fitted plot shows that the residuals are not evenly distributed along the
#horizonal line. This suggests that the assumption of constant variance might be violated.

#QQ plot shows that the residuals drift away from the dash line, which indicates a skewed distribution.
#Therefore, the assumption of normality is violated.

#The Scale-Location plot shows a similar pattern as seen in the non-standardized plot 
#that indicates a violation of homoscedasticity.

#The residuals vs leverage plot shows that the red line is not smooth and stays doesn't stay close to 
#the horizontal gray dashed line and there seem to be some influential points.

#2. Is there any multi-colinearity in multivariate models?
library(psych) #correlation matrix
# psych - include functions most useful for personality and psychological research
cor <- trip.data[,c("PRCP","SNWD","SNOW","Temp","Holiday","Weekday")]
pairs.panels(cor)

#The values of corelation co-efficient indicate there is relationship between SNWD & PRCP and SNOW & SNWD 
#although its a weak one.

library(car) #VIF test
vif(reg.model)
# NumberOfTrips ~ SNWD + SNOW

#No multicollinearity is detected in the model as all the variance inflation
#factor values are below the recommended threshold of 10 for critical multicollinearity levels.


## Stepwise Bidirectional Regression of choosing predictor variables 
library(MASS)
step <- stepAIC(reg.model, direction="both")


# When performing the stepwise regression, the results confirm that the best model (with the lowest
#AIC score) is the one with SNWD and SNOW as the predictor of NumberOfTrips. Adding the rest of the variables doesn't seem
#to improve the overall significance of the model. Therefore, they can be excluded.


step$anova 
#display results #ranking the significant predictor variables and excluding insignificant one from the model 


## The above final model is the best model as per the analysis given all those predictor variables.
