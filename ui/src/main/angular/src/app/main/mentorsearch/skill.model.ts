export class Skill {
    constructor( public id: number = undefined,
        public name: string = '',
        public description: string= '',
        public active=true,
        public selected=false)
        {}
}