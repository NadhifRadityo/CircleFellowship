package io.github.NadhifRadityo.CircleFellowship.Core.Object.Canvas.Shapes;

import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Square extends Rectangle {
	protected int s;

	public Square(int x, int y, int s) {
		super(x, y, s, s);
	}
	
	public int getLength() {
		return s;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Square))
			return false;
		if (!super.equals(other))
			return false;
		Square castOther = (Square) other;
		return Objects.equals(s, castOther.s);
	}
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), s);
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append("s", s).toString();
	}
}
