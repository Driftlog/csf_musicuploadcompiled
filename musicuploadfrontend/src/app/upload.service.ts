import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  http = inject(HttpClient)

  constructor() { }

  uploadFiles(files : File[]) {
    const formData = new FormData();
    files.forEach((fileData) => {
      formData.append('files', fileData)
    })

    console.log('uploading files...')

    firstValueFrom(this.http.post('/uploadmusic', formData)).catch((error) => {console.log(error)})
  }
}
