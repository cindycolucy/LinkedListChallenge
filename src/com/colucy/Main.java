package com.colucy;

import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<Album>();

    public static void main(String[] args) {

        Album album = new Album("The Joshua Tree", "U2");
        album.addSong("Where the Streets Have No Name", 4.22);
        album.addSong("Peace On Earth", 5.52);
        album.addSong("Joshua Tree", 3.34);
        albums.add(album);

        album = new Album("The White Album", "The Beatles");
        album.addSong("Within You, Without You", 3.45);
        album.addSong("Revolution", 5.45);
        album.addSong("Ob La Di", 3.23);
        albums.add(album);

        LinkedList<Song> playlist = new LinkedList<Song>();
        albums.get(0).addToPlaylist("Peace On Earth", playlist);
        albums.get(0).addToPlaylist("Joshua Tree", playlist);
        albums.get(0).addToPlaylist("What Is Up", playlist); // does not exist
        albums.get(0).addToPlaylist(2, playlist);
        albums.get(0).addToPlaylist("Ob La Di", playlist);
        albums.get(0).addToPlaylist("Revolution", playlist);
        albums.get(0).addToPlaylist(10, playlist); // does not exist

        play(playlist);



    }

    private static void play(LinkedList<Song> playlist){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playlist.listIterator();
        if(playlist.size() == 0){
            System.out.println("No songs in playlist");
            return;
        }else{
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while(!quit){
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action){
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;
                case 1:
                    if(!forward){
                        if(listIterator.hasNext()){
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if(listIterator.hasNext()){
                        System.out.println("Now playing " + listIterator.next().toString());
                    }else{
                        System.out.println("We hve reached the end of the playlist");
                        forward = false;
                    }
                    break;
                case 2:
                    if(forward){
                        if(listIterator.hasPrevious()){
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if(listIterator.hasPrevious()){
                        System.out.println("Now playing " + listIterator.previous().toString());
                    }else{
                        System.out.println("We hve reached the start of the playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if(forward){
                        if(listIterator.hasPrevious()){
                            System.out.println("Now replaying " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the playlist");
                        }

                    } else {
                        if (listIterator.hasNext()){
                            System.out.println("Now replaying " + listIterator.next().toString());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the list");
                        }
                    }
                    break;
                case 4:
                    printList(playlist);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if(playlist.size()>0){
                        listIterator.remove();
                        if(listIterator.hasNext()){
                            System.out.println("Now playing " + listIterator.next());
                        } else if (listIterator.hasPrevious()){
                            System.out.println("Now playing " + listIterator.previous());
                        }
                    }
                    break;
            }

        }
    }

    public static void printMenu(){
        System.out.println("Available actions:\npress");
        System.out.println("O - to quit\n" +
        "1 - to play the next song\n" +
        "2 - to play previous song\n" +
        "3 - to replay current song\n" +
        "4 - list songs in the playlist\n" +
        "5 - print available actions\n" +
        "6 - delete current song from playlist");
    }

    private static void printList(LinkedList<Song> playlist){
        Iterator<Song> iterator = playlist.iterator();
        System.out.println("====================");
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("====================");
    }
}
