public class LibraryMember {
    private String memberId;
    private String name;

    public LibraryMember(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryMember member = (LibraryMember) o;
        return memberId.equals(member.memberId);
    }
}
