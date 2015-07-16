package factory;

public class Car extends Product {

	Engine engine;
	Wheel [] wheels ;
	
	public Car() { 
		engine = new Engine();
		wheels = new Wheel[ 4 ];
		int index = 0;
		wheels[ index ++ ] = new Wheel();
		wheels[ index ++ ] = new Wheel();
		wheels[ index ++ ] = new Wheel();
		wheels[ index ++ ] = new Wheel();
	}

}
