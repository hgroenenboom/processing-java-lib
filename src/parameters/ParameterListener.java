package parameters;

public interface ParameterListener<T> 
{
	abstract void onValueChanged(Parameter<T> source, T newValue);
}
