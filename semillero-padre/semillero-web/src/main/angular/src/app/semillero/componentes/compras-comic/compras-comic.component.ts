import { Component, Input, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { GestionarComicService } from "../service/gestionar-comic.service";

@Component({
  selector: "app-compras-comic",
  templateUrl: "./compras-comic.component.html",
})
export class ComprasComicComponent implements OnInit {
  @Input() idComic: number;

  public gestionarForm: FormGroup;
  public submitted: boolean;
  private id: number;
  private cantidad: number;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private gestionComicsService: GestionarComicService,
    private route: ActivatedRoute
  ) {
    this.gestionarForm = this.fb.group({
      id: [null, Validators.required],
      cantidad: [null, Validators.required],
    });
  }

  ngOnInit() {
    this.submitted = false;
    this.id = this.idComic;
    this.route.paramMap.subscribe((params) => {
      if (params.has("id")) {
      }
    });
  }

  get f() {
    return this.gestionarForm.controls;
  }

  public ComprarComic() {
    this.submitted = true;
    if (this.gestionarForm.invalid) {
      return;
    }
    this.id = this.gestionarForm.controls.id.value;
    this.cantidad = this.gestionarForm.controls.cantidad.value;

    this.gestionComicsService
      .comprarComic(this.id, this.cantidad)
      .subscribe((data) => {
        if (data.exitoso) {
          console.log(
            "if-EL MENSAJE DE EJECUCION ES: " + data.mensajeEjecucion
          );
        } else {
          console.log(
            "else-EL MENSAJE DE EJECUCION ES: " + data.mensajeEjecucion
          );
        }
        this.limpiarDatosComic();
      });
  }
  private limpiarDatosComic(): void {
    this.submitted = false;
    this.gestionarForm.controls.id.setValue(null);
    this.gestionarForm.controls.cantidad.setValue(null);
  }
}
