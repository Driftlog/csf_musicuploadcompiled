import { Router } from '@angular/router';
import { Component, ElementRef, ViewChild, inject } from '@angular/core';
import { UploadService } from 'src/app/upload.service';

@Component({
  selector: 'app-uploadpage',
  templateUrl: './uploadpage.component.html',
  styleUrls: ['./uploadpage.component.css']
})
export class UploadpageComponent {

  @ViewChild('files') files !: ElementRef ;

  fileList : string[] = [];

  router = inject(Router)
  uploadSvc = inject(UploadService)

  uploadMusic() {

    console.log(this.files.nativeElement.files)

    this.uploadSvc.uploadFiles(Array.from(this.files.nativeElement.files))

    // this.router.navigate(['/playmusic'])
  }

  listFiles(event: any) {

    const fileArr : File[] = Array.from(event.target.files)

    fileArr.forEach((value: File) => {
      if (!this.fileList.includes(value.name)) {
        this.fileList.push(value.name)
      }
     })

     

  }





}
