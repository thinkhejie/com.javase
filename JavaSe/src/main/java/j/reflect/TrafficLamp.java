package j.reflect;

public enum TrafficLamp {
	RED(30) {
		@Override
		public TrafficLamp nextLamp() {
			return GREEN;
		}
	},
	GREEN(45) {
		@Override
		public TrafficLamp nextLamp() {
			return YELLOW;
		}
	},
	YELLOW(5) {
		@Override
		public TrafficLamp nextLamp() {
			return RED;
		}
	};
	public abstract TrafficLamp nextLamp();

	private int time;

	private TrafficLamp(int time) {
		this.time = time;
	}
}
