import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { HttpModule } from "@angular/http";
import { HttpClientModule, HttpClient } from "@angular/common/http";
import { AppRoutingModule } from "./app-routing.module";
import { APP_BASE_HREF } from "@angular/common";
import { ReactiveFormsModule } from "@angular/forms";

import { AppComponent } from "./app.component";
import { MenuComponent } from "./semillero/componentes/menu/menu-component";
import { BienvenidaComponent } from "./semillero/componentes/home/bienvenida-component";
import { CrearPersonaComponent } from "./semillero/componentes/crear-persona/crear-persona.component";
import { GestionarComicComponent } from "./semillero/componentes/gestionar-comic/gestionar-comic.component";
import {
  NgbdModalConfirm,
  NgbdModalConfirmAutofocus,
  NgbdModalFocus,
} from "./semillero/componentes/modal-focus/modal-focus.component";
import { ComprasComicComponent } from "./semillero/componentes/compras-comic/compras-comic.component";

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    BienvenidaComponent,
    CrearPersonaComponent,
    GestionarComicComponent,
    NgbdModalConfirm,
    NgbdModalConfirmAutofocus,
    NgbdModalFocus,
    ComprasComicComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [{ provide: APP_BASE_HREF, useValue: "/SemilleroHBT" }],
  bootstrap: [AppComponent],
})
export class AppModule {}
