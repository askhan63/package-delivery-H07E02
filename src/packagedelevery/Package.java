package packagedelevery;

import java.util.Objects;

public class Package implements Comparable<Package> {
	private String sender;
	private String address;
	private double weight;
        private boolean loaded;
	public Package(String sender, String address, double weight) {
		this.sender = sender;
		this.address = address;
		this.weight = weight;
                this.loaded=false;
	}

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String toString() {
		return "Package from \"" + sender + "\" to \"" + address + "\" with weight " + weight;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		Package otherPackage = (Package) other;
		return Double.compare(weight, otherPackage.weight) == 0
		       && Objects.equals(address, otherPackage.address)
		       && Objects.equals(sender, otherPackage.sender);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sender, address, weight);
	}

  
    @Override
    public int compareTo(Package o) {
         if(this.weight>o.weight)
             return -10;
         else if(this.weight==o.weight)
             return 0;
         else
             return 1;
    }
}
