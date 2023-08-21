let credentials = [
    {
        userName: "ayush",
        password: "ayushkumar26"
    },
    {
        userName: "user1",
        password: "12345"
    },
    {
        userName: "user2",
        password: "1234"
    },
    {
        userName: "user3",
        password: "1234"
    }
]

export function isPresent(username, password) {
    return credentials.some(user => (username === user.userName && password === user.password));
}
