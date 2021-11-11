import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { BienvenidaComponent } from "./semillero/componentes/home/bienvenida-component";
import { GestionarComicComponent } from "./semillero/componentes/gestionar-comic/gestionar-comic.component";
import {
  NgbdModalConfirm,
  NgbdModalConfirmAutofocus,
  NgbdModalFocus,
} from "./semillero/componentes/modal-focus/modal-focus.component";
import { ComprasComicComponent } from "./semillero/componentes/compras-comic/compras-comic.component";

const routes: Routes = [
  { path: "bienvenida", component: BienvenidaComponent, data: null },
  { path: "gestionar-comic", component: GestionarComicComponent },
  { path: "modal-focus", component: NgbdModalFocus },
  { path: "comprar-comic", component: ComprasComicComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
