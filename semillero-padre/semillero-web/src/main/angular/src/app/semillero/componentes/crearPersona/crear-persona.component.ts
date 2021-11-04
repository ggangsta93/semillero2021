import { Component, OnInit } from "@angular/core";

@Component({
  selector: "crear-persona",
  templateUrl: "./crear-persona.component.html",
  styleUrls: ["./crear-persona.component.css"],
})
export class CrearPersonaComponent implements OnInit {
  private nombreInstructor: String;

  constructor() {}

  ngOnInit() {
    this.nombreInstructor = "Javier Arias";
    let edadInstructor: number = 33;

    if (edadInstructor < 33) {
      let resultadoCalculo = 12;
    }
  }
}
