package parameters;

public interface ParameterListener<T> 
{
	abstract void onValueChanged(T newValue);
}
