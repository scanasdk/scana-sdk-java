
package com.knownsec.sdk.validation.validator.size;


import com.knownsec.sdk.validation.limitation.Size;

public class SizeValidatorForArraysOfByte extends AbstractSizeValidator<byte[]> {

	public SizeValidatorForArraysOfByte(Size size) {
		super(size);
	}

	@Override
	public boolean isValid(byte[] value) {
		if ( value == null ) {
			return true;
		}
		return value.length >= min && value.length <= max;
	}
}
