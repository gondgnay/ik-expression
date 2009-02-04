/**
 * 
 */
package org.wltea.expression.op.define;

import org.wltea.expression.IllegalExpressionException;
import org.wltea.expression.datameta.BaseDataMeta;
import org.wltea.expression.datameta.Constant;
import org.wltea.expression.op.IOperatorExecution;
import org.wltea.expression.op.Operator;

/**
 * 算术取模操作
 * @author 林良益
 * Sep 26, 2008
 * @version 2.0
 */
public class Op_MOD implements IOperatorExecution {

	public static final Operator THIS_OPERATOR = Operator.MOD;
	
	
	/* (non-Javadoc)
	 * @see org.wltea.expression.op.IOperatorExecution#execute(org.wltea.expression.ExpressionToken[])
	 */
	public Constant execute(Constant[] args) {

		if(args == null || args.length != 2){
			throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "参数个数不匹配");
		}

		Constant first = args[1];
		if(null == first || null == first.getDataValue()){
			//抛NULL异常
			throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
		}
		
		Constant second = args[0];
		if(null == second || null == second.getDataValue()){
			//抛NULL异常
			throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
		}

		if( BaseDataMeta.DataType.DATATYPE_NULL ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_NULL ==  second.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_BOOLEAN ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_BOOLEAN ==  second.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_DATE ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_DATE ==  second.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_STRING ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_STRING ==  second.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_COLLECTION ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_COLLECTION ==  second.getDataType()
			){
			//抛异常
			throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数类型错误"	);

		}else if( Double.compare(second.getDoubleValue(), 0) == 0){
			//除数为零
			throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "\"除数为零");
			
		}else if(BaseDataMeta.DataType.DATATYPE_DOUBLE == first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_DOUBLE == second.getDataType()){

			Double result = first.getDoubleValue() % second.getDoubleValue();
			return new Constant(BaseDataMeta.DataType.DATATYPE_DOUBLE , result);
			
		}else if(BaseDataMeta.DataType.DATATYPE_FLOAT == first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_FLOAT == second.getDataType()){

			Float result = first.getFloatValue() % second.getFloatValue();
			return new Constant(BaseDataMeta.DataType.DATATYPE_FLOAT , result);
			
		}else if(BaseDataMeta.DataType.DATATYPE_LONG == first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_LONG == second.getDataType()){

			Long result = first.getLongValue() % second.getLongValue();
			return new Constant(BaseDataMeta.DataType.DATATYPE_LONG , result);
			
		}else {

			Integer result = first.getIntegerValue() % second.getIntegerValue();
			return new Constant(BaseDataMeta.DataType.DATATYPE_INT , result);
		}				
	}

	/* (non-Javadoc)
	 * @see org.wltea.expression.op.IOperatorExecution#verify(int, org.wltea.expression.ExpressionToken[])
	 */
	public Constant verify(int opPositin, BaseDataMeta[] args)
			throws IllegalExpressionException {

		if(args == null){
			throw new IllegalArgumentException("运算操作符参数为空");
		}
		if(args.length != 2){
			//抛异常
			throw new IllegalExpressionException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数个数不匹配"
						, THIS_OPERATOR.getToken()
						, opPositin
					);
		}
		
		BaseDataMeta first = args[1];
		BaseDataMeta second = args[0];
		if(first == null || second == null){
			throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
		}		

		if( BaseDataMeta.DataType.DATATYPE_NULL ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_NULL ==  second.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_BOOLEAN ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_BOOLEAN ==  second.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_DATE ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_DATE ==  second.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_STRING ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_STRING ==  second.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_COLLECTION ==  first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_COLLECTION ==  second.getDataType()
				){
			//抛异常
			throw new IllegalExpressionException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数类型错误"
					, THIS_OPERATOR.getToken()
					, opPositin
					);
		}else if(BaseDataMeta.DataType.DATATYPE_DOUBLE == first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_DOUBLE == second.getDataType()){
			return new Constant(BaseDataMeta.DataType.DATATYPE_DOUBLE , Double.valueOf(0.0));
			
		}else if(BaseDataMeta.DataType.DATATYPE_FLOAT == first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_FLOAT == second.getDataType()){
			return new Constant(BaseDataMeta.DataType.DATATYPE_FLOAT , Float.valueOf(0.0f));
			
		}else if(BaseDataMeta.DataType.DATATYPE_LONG == first.getDataType()
				|| BaseDataMeta.DataType.DATATYPE_LONG == second.getDataType()){
			return new Constant(BaseDataMeta.DataType.DATATYPE_LONG , Long.valueOf(0L));
			
		}else {
			return new Constant(BaseDataMeta.DataType.DATATYPE_INT , Integer.valueOf(0));
		}			
		
	}

}