package C6;

import java.util.BitSet;

public class Main {

	public static void main(String[] args) {
		DataBase dataBase=new DataBase();
		dataBase.add(new DataDetail(100,BitSet.valueOf(new long[]{38})));
		dataBase.add(new DataDetail(200,BitSet.valueOf(new long[]{20})));
		dataBase.add(new DataDetail(300,BitSet.valueOf(new long[]{12})));
		dataBase.add(new DataDetail(400,BitSet.valueOf(new long[]{22})));
		dataBase.add(new DataDetail(500,BitSet.valueOf(new long[]{10})));
		dataBase.add(new DataDetail(600,BitSet.valueOf(new long[]{12})));
		dataBase.add(new DataDetail(700,BitSet.valueOf(new long[]{10})));
		dataBase.add(new DataDetail(800,BitSet.valueOf(new long[]{46})));
		dataBase.add(new DataDetail(900,BitSet.valueOf(new long[]{14})));
		Apriori test=new Apriori(2, dataBase);
		test.output();

	}

}
