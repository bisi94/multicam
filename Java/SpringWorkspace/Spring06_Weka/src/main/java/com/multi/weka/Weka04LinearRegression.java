package com.multi.weka;

import org.springframework.ui.Model;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/*
 sellingPrice =

    -26.6882 * houseSize +
      7.0551 * lotSize +
  43166.0767 * bedrooms +
  42292.0901 * bathroom +
 -21661.1208
 * */
public class Weka04LinearRegression {
	
	public static void main(String[] args) throws Exception{
		DataSource ds=new DataSource("C:/Weka-3-9/data/house/house.arff");
		Instances data=ds.getDataSet();
		data.setClassIndex(data.numAttributes()-1);//y값(target)-종속변수
		
		LinearRegression model=new LinearRegression();
		model.buildClassifier(data);
		System.out.println("LinearRegression model 공식: "+model);
		
		Instance firstHouse=data.firstInstance();
		double predictPrice=model.classifyInstance(firstHouse);
		System.out.println("첫번째 집 예측 가격: "+ predictPrice);
		System.out.println("첫번째 집 실제 호가: "+firstHouse.classValue());
		
		Instance fourthHouse=data.instance(3);
		predictPrice=model.classifyInstance(fourthHouse);
		System.out.println("4번째 집 예측 가격: "+ predictPrice);
		System.out.println("4번째 집 실제 호가: "+fourthHouse.classValue());
		
		predictPrice=predictCalc(2983,9365,5,1);
		System.out.println("----------myhouse 예상 가격----------");
		System.out.println(predictPrice);
		
		predictHouse(model, "C:/Weka-3-9/data/house/myhouse.arff");
	}//-----------------------------------------
	
	static public void predictHouse(LinearRegression model, String file) throws Exception{
		DataSource ds=new DataSource(file);
		Instances dataset=ds.getDataSet();
		dataset.setClassIndex(dataset.numAttributes()-1);
		Instance myHouse=dataset.instance(0);
		System.out.println("*************************************************");
		System.out.println("myHouse 예측 가격: "+model.classifyInstance(myHouse));
		System.out.println("myHouse 호가: "+myHouse.classValue());
		System.out.println("*************************************************");
		
	}
	
	static public double predictCalc(double houseSize, double lotSize, int bedrooms, int bathroom) {
		double sellPrice=-26.6882 * houseSize +
					      7.0551 * lotSize +
					      43166.0767 * bedrooms +
					      42292.0901 * bathroom +
					     -21661.1208;
		return sellPrice;
	}
	
}
