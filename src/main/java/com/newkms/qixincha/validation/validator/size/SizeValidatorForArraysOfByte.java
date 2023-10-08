
package com.newkms.qixincha.validation.validator.size;


import com.newkms.qixincha.validation.limitation.Size;

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
