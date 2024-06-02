import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BarcoItemComponent } from './barco-item.component';

describe('BarcoItemComponent', () => {
  let component: BarcoItemComponent;
  let fixture: ComponentFixture<BarcoItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BarcoItemComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BarcoItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
