import { Component, inject } from '@angular/core';
import { UpperCasePipe, Location } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Hero } from '../hero';
import { HeroService } from '../hero.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.css'],
  imports: [FormsModule, UpperCasePipe],
})
export class HeroDetailComponent {
  hero: Hero | undefined;
  newPower: string = ''; // ✅ Para almacenar el nuevo superpoder

  constructor(
    private route: ActivatedRoute,
    private heroService: HeroService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getHero();
  }

  getHero(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.heroService.getHero(id).subscribe(hero => this.hero = hero);
  }

  goBack(): void {
    this.location.back();
  }

  addPower(): void {
    if (this.newPower.trim() && this.hero) {
      this.hero.powers.push(this.newPower.trim());
      this.newPower = '';
    }
  }

  removePower(index: number): void {
    if (this.hero) {
      this.hero.powers.splice(index, 1);
    }
  }
}
