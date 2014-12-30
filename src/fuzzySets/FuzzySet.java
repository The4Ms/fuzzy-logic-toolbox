package fuzzySets;

public interface FuzzySet {
	public abstract double getMembershipCertainty(double val);
	public abstract void setCentroid(double val);
	public abstract double getCentroid();
}
