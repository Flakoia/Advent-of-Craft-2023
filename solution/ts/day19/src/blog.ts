import {Either, left, right} from "fp-ts/Either";

export class Article {
    private constructor(private readonly name: string,
                        private readonly content: string,
                        private readonly comments: Comment[]) {
    }

    public static create = (name: string, content: string): Article => new Article(name, content, []);

    public addComment = (text: string, author: string): Either<Error, Article> =>
        this.addCommentWithCreationDate(text, author, new Date());

    public getComments = (): Comment[] => this.comments;

    private addCommentWithCreationDate = (text: string, author: string, creationDate: Date): Either<Error, Article> => {
        const comment = new Comment(text, author, creationDate);

        return this.comments.some(c => c.isSame(comment))
            ? left({message: "Comment already exists"})
            : right(new Article(this.name, this.content, [...this.comments, comment]));
    };
}

export type Error = { message: string }

export class Comment {
    constructor(
        public readonly text: string,
        public readonly author: string,
        public readonly creationDate: Date
    ) {
    }

    isSame = (otherComment: Comment) => this.text === otherComment.text
        && this.author === otherComment.author
        && this.creationDate.getDate() === otherComment.creationDate.getDate();
}
