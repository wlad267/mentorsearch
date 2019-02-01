import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../mentorsearch/user.model';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  users: Array<User>;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.fetchAllUserts().subscribe((users: Array<User>) => {
      this.users = users;
    });
  }

}
