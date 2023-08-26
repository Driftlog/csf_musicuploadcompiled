import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { UploadpageComponent } from './components/uploadpage/uploadpage.component';
import { PlaymusicComponent } from './components/playmusic/playmusic.component';
import {HttpClientModule} from '@angular/common/http'

const routes : Routes = [
  {path: '', component: UploadpageComponent},
  {path: 'playmusic', component: PlaymusicComponent},
  {path: '**',  redirectTo: '/'}
]

@NgModule({
  declarations: [
    AppComponent,
    UploadpageComponent,
    PlaymusicComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes, {useHash: true}),
    HttpClientModule
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
