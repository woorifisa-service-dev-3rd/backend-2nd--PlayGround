package playground.model;

public enum FianceType {
	bank, Securities, national_tax, local_tax;
	public static FianceType fromCode(int code) {
		switch (code) {
			case 1:
				return bank;
			case 2:
				return Securities;
			case 3:
				return national_tax;
			case 4:
				return local_tax;
			default:
				throw new IllegalArgumentException("Invalid finance code: " + code);
		}
	}

	}
