package com.qian.vo;




/**
 * @author Lu Kongwen
 * @version Create time：2015-12-25 上午10:27:01
 * @Description
 */
public class BaseDto<T> {

	private int code;
	private String msg;
	private T data;
	
	public BaseDto(){
		
	}
	
	public BaseDto(CommonCode resultCode) {
		this.code = resultCode.getCode();
	}

	/**
	 * 不确定返回
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> BaseDto<T> getUnKnowResult(T data, CommonCode resultCode) {

		if (resultCode.getCode() == CommonCode.OK.getCode()) {
			return getSuccessResult(data);
		} else {
			return getFailResult(resultCode);
		}

	}
	
	@SuppressWarnings("unchecked")
	public static <T> BaseDto<T> getResult(CommonCode commonCode, T data) {
		
		if (commonCode.getCode() != CommonCode.OK.getCode()) {
			return BaseDto.getFailResult(commonCode);
		}
		
		return BaseDto.getSuccessResult(data);
	}

	/**
	 * 获取正确信息 有返回值
	 * 
	 * @return
	 */
	public static <T> BaseDto<T> getSuccessResult(T data) {
		BaseDto<T> result = new BaseDto<T>(CommonCode.OK);
		result.setData(data);
		return result;
	}

	/**
	 * 组装错误信息
	 * 
	 * @param resultCode
	 *            对应 ResultCode枚举类
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static BaseDto getFailResult(CommonCode resultCode) {
		BaseDto result = new BaseDto(resultCode);
		result.setMsg(resultCode.getMsg());
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public static BaseDto getFailResult(CommonCode resultCode,String msg) {
		BaseDto result = new BaseDto(resultCode);
		result.setMsg(msg);
		return result;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BaseDto [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

	
}
