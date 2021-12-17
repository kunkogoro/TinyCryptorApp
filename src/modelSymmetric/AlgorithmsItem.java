package modelSymmetric;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmsItem {

	private String algorithms;
	private List<Integer> keySize;
	private List<String> mode;
	private List<String> padding;

	public AlgorithmsItem() {
		init();
		loadData();
	}
	
	
	public AlgorithmsItem(String algorithms, List<Integer> keySize, List<String> mode, List<String> padding) {
		super();
		this.algorithms = algorithms;
		this.keySize = keySize;
		this.mode = mode;
		this.padding = padding;
	}


	public void loadData() {
	
	}
   public void init() {
	   keySize = new ArrayList<>();
	   mode = new ArrayList<>();
	   padding = new ArrayList<>();
   }
	public String getAlgorithms() {
		return algorithms;
	}

	public void setAlgorithms(String algorithms) {
		this.algorithms = algorithms;
	}

	public List<Integer> getKeySize() {
		return keySize;
	}

	public void setKeySize(List<Integer> keySize) {
		this.keySize = keySize;
	}

	public List<String> getMode() {
		return mode;
	}

	public void setMode(List<String> mode) {
		this.mode = mode;
	}

	public List<String> getPadding() {
		return padding;
	}

	public void setPadding(List<String> padding) {
		this.padding = padding;
	}
	
	public static void main(String[] args) {
		AlgorithmsItem algorithmsItem = new AlgorithmsItem();
		System.out.println(algorithmsItem.getAlgorithms());
	}
	
	
}
