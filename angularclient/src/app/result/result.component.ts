import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  results: any = [];

  constructor(public rest: RestService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.getResults();
  }

  getResults() {
    this.results = [];
    this.rest.getResults().subscribe((data: {}) => {
      console.log(data);
      this.results = data;
    });
  }

  upload() {
    this.router.navigate(['/result-analyze']);
  }

}
