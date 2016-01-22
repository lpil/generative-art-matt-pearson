int size = 1;
float stepSize = 0.1;

public class Mover {
  public float distance;
  public int angle;
  public String direction;

  void step() {
    int newAngle   = angle + 1;
    float startRad = radians(angle);
    rotate(startRad);
    arc(0, 0, distance, distance, 0, radians(1));
    this.angle = newAngle;
    this.distance = this.distance + stepSize;
   }
}

Mover[] movers = new Mover[10];

void setup() {
  size(1080, 720);
  background(0);
  stroke(255);
  noFill();


  for (int i = 0; i < size; i++) {
    Mover mover = new Mover();
    mover.distance = 0;
    mover.angle = 0;
    mover.direction = "forwards";
    movers[i] = mover;
  }
}

void draw() {
  translate(width / 2, height / 2);
  for (int i = 0; i < size; i++) {
    Mover mover = movers[i];
    mover.step();
  }
}